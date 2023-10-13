package net.example.CrudDemo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@Document(collection="users")
public class User {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String address;
    private List<List<String>> phoneEntries;
}