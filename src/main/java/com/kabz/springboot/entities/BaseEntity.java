package com.kabz.springboot.entities;

import jakarta.persistence.*;
import lombok.*;
import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@MappedSuperclass
@Getter
public class BaseEntity {
    static final String JODA_DATETIME = "org.jadira.usertype.dateandtime.joda.PersistentDateTime";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(insertable = false, updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(insertable = false)
    @LastModifiedDate
    private Instant modifiedDate;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.id == null || obj == null || !(this.getClass().equals(obj.getClass())))
            return false;

        BaseEntity that = (BaseEntity) obj;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

}
