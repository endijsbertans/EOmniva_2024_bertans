package model;

public class CustomerAsCompany extends AbstractCustomer{
    private String title;
    private String companyRegNo;
    public CustomerAsCompany(){
        super();
        setTitle(null);
        setCompanyRegNo(null);
        setCustomerCode();
    }

    public CustomerAsCompany(Address address, String phone,String title, String companyRegNo){
        super(address, phone);
        setTitle(title);
        setCompanyRegNo(companyRegNo);
        setCustomerCode();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if(title != null && title.length() < 30)
            this.title = title;
        else
            this.title = "---";
    }

    public String getCompanyRegNo() {
        return companyRegNo;
    }

    public void setCompanyRegNo(String companyRegNo) {
        if(title != null &&
                title.matches("\\d{11}"))
            this.companyRegNo = companyRegNo;
        else
            this.companyRegNo = "12345678";
    }

    @Override
    public String toString() {
        return  super.toString() +
                "CustomerAsCompany{" +
                "title='" + title + '\'' +
                ", companyRegNo='" + companyRegNo + '\'' +
                ", CustomerCode='" + getCustomerCode() + '\'' +
                '}';
    }

    @Override
    public void setCustomerCode() {
        super.customerCode  = getcID() + "_person_" + companyRegNo;
    }
}
