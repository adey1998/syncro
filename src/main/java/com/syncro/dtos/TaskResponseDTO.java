package com.syncro.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponseDTO {
    
    private Long id;
    private String title;
    private String description;

    public TaskResponseDTO(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
