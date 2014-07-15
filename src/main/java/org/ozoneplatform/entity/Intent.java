package org.ozoneplatform.entity;

public class Intent extends Entity {
    private boolean send;
    private boolean receive;
    private String action;
    private String dataType;

    public boolean getSend() { return send; }
    public boolean getReceive() { return receive; }
    public String getAction() { return action; }
    public String getDataType() { return dataType; }

    public void setSend(boolean send) { this.send = send; }
    public void setReceive(boolean receive) { this.receive = receive; }
    public void setAction(String action) { this.action = action; }
    public void setDataType(String dataType) { this.dataType = dataType; }
}
