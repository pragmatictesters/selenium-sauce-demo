package examples.support;

public interface ICheck {

    boolean isChecked();

    boolean isEnabled();

    boolean isVisible();

    String getLabel();

    void check();

    void uncheck();

    void toggle();


}
