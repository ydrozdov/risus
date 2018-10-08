package com.ydrozdov.smileco;

import com.ydrozdov.smileco.db.entities.PersonEntity;
import com.ydrozdov.smileco.domain.model.dto.CoupleDTO;
import com.ydrozdov.smileco.domain.model.dto.PersonDTO;
import com.ydrozdov.smileco.domain.model.enums.RelationshipType;
import com.ydrozdov.smileco.services.PersonService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping(path = "/people")
public class PeopleController {

    @Autowired
    private PersonService personService;

    private ModelMapper modelMapper;

    @Autowired
    public PeopleController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
    }

    @PostMapping(path = "")
    @ResponseBody
    public ResponseEntity addPerson(@Valid @RequestBody final PersonDTO personDTOModel) {
        PersonEntity person = modelMapper.map(personDTOModel, PersonEntity.class);
        int personId = personService.addPerson(person);
        personService.addParenthood(personId, personDTOModel.getFirstParentId());
        personService.addParenthood(personId, personDTOModel.getSecondParentId());

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity getById(@Valid @Positive @PathVariable final int id) {
        Optional<PersonEntity> person = personService.findById(id);
        if (person.isPresent()) {
            return new ResponseEntity<>(person.get(), HttpStatus.OK);
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonEntity was not found");
    }

    @GetMapping(path = "")
    @ResponseBody
    public Iterable<PersonEntity> getAllByName(
            @RequestParam(value = "name", defaultValue = "", required = false) final String name
        ) {
        return personService.findAllByName(name);
    }

    @PostMapping(path = "/relationships")
    @ResponseBody
    public ResponseEntity addMarriage(@Valid @RequestBody final CoupleDTO coupleDTOModel) {
        int response = personService.addMarriage(coupleDTOModel.getPerson1Id(),coupleDTOModel.getPerson2Id());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = "/relationships/{id}")
    @ResponseBody
    public ResponseEntity endMarriage(@Valid @Positive @PathVariable final int id) {
        int response = personService.endMarriage(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(path = "/relationships")
    @ResponseBody
    public ResponseEntity getAllByName(
            @Valid @Positive(message="Person should be a positive number")
            @RequestParam(value = "person") final int person1id,
            @Valid @Positive(message="Relative should be a positive number")
            @RequestParam(value = "relative") final int person2id
    ) {
        ArrayList<String> relationships = personService.getRelationships(person1id, person2id);
        return new ResponseEntity<>(relationships, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
