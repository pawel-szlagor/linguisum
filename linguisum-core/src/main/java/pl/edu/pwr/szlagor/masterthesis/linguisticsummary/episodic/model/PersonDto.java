package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model;

public class PersonDto {

    private Long id;
    private String name;

    @java.beans.ConstructorProperties({ "id", "name" })
    public PersonDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PersonDto() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PersonDto))
            return false;
        final PersonDto other = (PersonDto) o;
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
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof PersonDto;
    }

    public String toString() {
        return "pl.edu.pwr.szlagor.masterthesis.linguisticsummary.episodic.model.PersonDto(id=" + this.getId() + ", name=" + this.getName()
                + ")";
    }
}
