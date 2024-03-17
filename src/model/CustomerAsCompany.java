package model;

public class CustomerAsCompany extends AbstractCustomer{
    private String title;
    private String companyRegNo;
    CustomerAsCompany(){
        super();
    }

    CustomerAsCompany(Address address, String phone,String title, String companyRegNo){
        super();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title != null &&
                title.matches("[A-ZĒŪĪĻĶĢŠĀČŅ]{1}[a-zēūīļķģšāžčņ]"))
            this.title = title;
        else
            this.title = "---";
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(String companyRegNo) {
        this.companyRegNo = companyRegNo;
    }

    @Override
    public String toString() {
        return "CustomerAsCompany{" +
                "title='" + title + '\'' +
                ", companyRegNo='" + companyRegNo + '\'' +
                '}';
    }

    @Override
    public void setCustomerCode(String customerCode) {
        setCustomerCode(getcID() + "_person_" + companyRegNo);
    }
}
