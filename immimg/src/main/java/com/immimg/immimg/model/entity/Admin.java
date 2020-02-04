package com.immimg.immimg.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends Person{
    private String name;

    @Override
    public String toString() {
        return super.toString()+",Admin{" +
                "name='" + name + '\'' +
                '}';
    }
}
