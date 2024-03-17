package model;

public class Driver extends Person{
    private long dID;
    private String licenseNo;
    private float experienceInYears;
    private static long counter = 0;
    public Driver(){
        super();
        setdID();
        setLicenseNo(null);
        setExperienceInYears(-1);
    }
    public Driver(String name, String surname, String personCode, String licenseNo, float experienceInYears){
        super(name, surname, personCode);
        setdID();
        setLicenseNo(licenseNo);
        setExperienceInYears(experienceInYears);
    }

    public long getdID() {
        return dID;
    }

    private void setdID() {
        this.dID = counter++;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        if(licenseNo != null &&
                licenseNo.matches("[A]{1}[F]{1}\\d{5,7}"))
            this.licenseNo = licenseNo;
        else
            this.licenseNo = "AFXXXXXX";
    }

    public float getExperienceInYears() {
        return experienceInYears;
    }

    public void setExperienceInYears(float experienceInYears) {
        if(experienceInYears >= 0 && experienceInYears <= 99)
            this.experienceInYears = experienceInYears;
        else
            this.experienceInYears = -1;
    }

    @Override
    public String toString() {
        return     super.toString() +
                "Driver{" +
                "dID=" + dID +
                ", licenseNo='" + licenseNo + '\'' +
                ", experienceInYears=" + experienceInYears +
                '}';
    }
}
