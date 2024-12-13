package sparta.scheduleservicedevelop.shared.tools.bcrypt;

public interface Encoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String encodedPassword);
}
