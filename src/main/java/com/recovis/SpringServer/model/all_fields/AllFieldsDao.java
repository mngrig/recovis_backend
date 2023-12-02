package com.recovis.SpringServer.model.all_fields;

import com.recovis.SpringServer.model.patient.Patient;
import com.recovis.SpringServer.model.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AllFieldsDao {

    @Autowired
    private AllFieldsRepository repository;

    public AllFields save(AllFields field){
        return repository.save(field);
    }

    public void AllFields(AllFields field){
        repository.delete(field);
    }

    public List<AllFields> getAllFields(){
        List<AllFields> allFields = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(allFields::add);
        return allFields;
    }

    public Optional<AllFields> searchField(String field_description_gr){
        List<AllFields> allFields;
        allFields = getAllFields();
        System.out.println(allFields);
        for(int i = 0 ; i < allFields.size(); i++) {
            if(allFields.get(i).getDescription_gr().equals(field_description_gr))
            {
                return Optional.of((allFields.get(i)));
            }
        }
        return Optional.empty();
    }


}
