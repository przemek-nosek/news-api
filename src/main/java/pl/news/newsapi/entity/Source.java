
package pl.news.newsapi.entity;

import javax.annotation.Generated;
import javax.persistence.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Generated("jsonschema2pojo")
@Entity

public class Source {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long source_id;

    @SerializedName("id")
    @Expose
    @Transient
    private String nameId;

    @SerializedName("name")
    @Expose
    private String name;


    public String getNameId() {
        return nameId;
    }

    public void setNameId(String nameId) {
        this.nameId = nameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Source)) return false;
        Source source = (Source) o;
        return Objects.equals(nameId, source.nameId) && Objects.equals(name, source.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameId, name);
    }
}
