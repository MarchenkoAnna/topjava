package ru.javawebinar.topjava.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * User: gkislin
 * Date: 22.08.2014
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class BaseEntity {
    public static final int START_SEQ = 100000;


    //@SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1)//, generator = "global_seq")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "INT(10) unsigned", nullable = false)
    protected Integer id;

    public BaseEntity() {
    }

    protected BaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return (this.id == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id;
    }

}
