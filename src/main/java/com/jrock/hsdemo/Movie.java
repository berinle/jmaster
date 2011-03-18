package com.jrock.hsdemo;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;

/**
 * @author berinle
 */

@Entity
@Table(name="MOVIE")
@Indexed
public class Movie {
    @DocumentId
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Field
    String title;
    @Field
    int runtime = 90;
    @Field
    String genre;

    @Version
    long version = 0;

    //relationships
    //casts, locations, writer, producer, e.t.c

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
