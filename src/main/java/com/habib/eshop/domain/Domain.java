package com.habib.eshop.domain;

import java.time.LocalDateTime;

public class Domain {
    private Long version;
    private Long id;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private LocalDateTime dateLastUpdated = LocalDateTime.now();

    public void setVersion(Long version) {
        this.version = version;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setDateLastUpdated(LocalDateTime dateLastUpdated) {
        this.dateLastUpdated = dateLastUpdated;
    }

    public Long getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateLastUpdated() {
        return dateLastUpdated;
    }
}
