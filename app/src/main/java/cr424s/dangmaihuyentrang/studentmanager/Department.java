package cr424s.dangmaihuyentrang.studentmanager;

public class Department {
    private String name,Phone,Description;

    public Department(String description, String phone, String name) {
        Description = description;
        Phone = phone;
        this.name = name;
    }

    public Department() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", Phone='" + Phone + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
