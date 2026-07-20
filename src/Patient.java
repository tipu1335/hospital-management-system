public class Patient extends Person{
    private String disease;
    private String assignedDoctorId;
    public Patient(String id,String name, int age,String gender,String disease){
        super(id,name,age,gender);
        this.disease=disease;
        this.assignedDoctorId=null;
    }
    public String getDisease(){
        return disease;
    }
    public void setAssignedDoctorId(String doctorId){
        this.assignedDoctorId=doctorId;
    }
    public String getAssignedDoctorId(){
        return assignedDoctorId;
    }
    @Override
    public void displayInfo(){
        System.out.println("Patient ID: "+id);
        System.out.println("Name: "+name);
        System.out.println("Age: "+age);
        System.out.println("Gender: "+gender);
        System.out.println("Disease: "+disease);
        System.out.println("Assigned Doctor ID: "+(assignedDoctorId==null ? "Not Assigned" : assignedDoctorId));
    }
}
