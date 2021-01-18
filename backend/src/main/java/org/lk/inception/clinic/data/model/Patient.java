package org.lk.inception.clinic.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient extends BaseModel {
    @Id
    private String id;

    private String name;

    private Integer age;

    private Character gender;
}
