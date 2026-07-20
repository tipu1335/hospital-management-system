public abstract class Person {
    protected String id;
    protected String name;
    protected int age;
    protected String gender;
    public Person(String id, String name, int age, String gender) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
    public abstract void displayInfo();


}
