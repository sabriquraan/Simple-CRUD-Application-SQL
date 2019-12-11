package util;

enum Constants {
    URL  ("jdbc:mysql://127.0.0.1:3306/employee?useSSL=false"),
    USERNAME("root"),
    PASSWORD   ( "sampop123"),
    DRIVER("com.mysql.jdbc.Driver")
    ;

    private final String configure;

    private Constants(String configure) {
        this.configure = configure;
    }
    public String getConfigure() {
        return this.configure;
    }
}