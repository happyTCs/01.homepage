package xu.walt.com.homepage.DispatchPKP.event;

public class MessageEvent {
    private String message;
    private boolean isCasual;
    private boolean isKey;
    private boolean isDirectional;

    public MessageEvent() {

    }
    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isCasual() {
        return isCasual;
    }

    public void setCasual(boolean casual) {
        isCasual = casual;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setKey(boolean key) {
        isKey = key;
    }

    public boolean isDirectional() {
        return isDirectional;
    }

    public void setDirectional(boolean directional) {
        isDirectional = directional;
    }
}
