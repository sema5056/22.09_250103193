public class Main {
    public static void main(String[] args) {
        Image guestImage = new ProxyImage("salary_sheet.png", "GUEST");
        Image adminImage = new ProxyImage("salary_sheet.png", "ADMIN");
        guestImage.show();
        adminImage.show();
    }
}
