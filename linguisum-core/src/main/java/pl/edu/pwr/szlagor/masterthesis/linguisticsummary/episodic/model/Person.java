package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.mongodb.morphia.annotations.Entity;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String mail;

    @java.beans.ConstructorProperties({ "id", "name", "mail" })
    public Person(Long id, String name, String mail) {
        this.id = id;
        this.name = name;
        this.mail = mail;
    }

    public Person() {
    }

    public static PersonBuilder builder() {
        return new PersonBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getMail() {
        return this.mail;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Person))
            return false;
        final Person other = (Person) o;
        if (!other.canEqual((Object) this))
            return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name))
            return false;
        final Object this$mail = this.getMail();
        final Object other$mail = other.getMail();
        if (this$mail == null ? other$mail != null : !this$mail.equals(other$mail))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $mail = this.getMail();
        result = result * PRIME + ($mail == null ? 43 : $mail.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Person;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person(id=" + this.getId() + ", name=" + this.getName()
                + ", mail=" + this.getMail() + ")";
    }

    public static class PersonBuilder {
        private Long id;
        private String name;
        private String mail;

        PersonBuilder() {
        }

        public Person.PersonBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Person.PersonBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Person.PersonBuilder mail(String mail) {
            this.mail = mail;
            return this;
        }

        public Person build() {
            return new Person(id, name, mail);
        }

        public String toString() {
            return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.Person.PersonBuilder(id=" + this.id + ", name="
                    + this.name + ", mail=" + this.mail + ")";
        }
    }
}
