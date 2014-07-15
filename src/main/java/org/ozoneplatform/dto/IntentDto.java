package org.ozoneplatform.dto;

import org.ozoneplatform.entity.Intent;
import org.ozoneplatform.entity.Id;


public class IntentDto implements OutDto<Intent>, InDto<Intent> {
    private Intent entity;

    IntentDto(Intent entity) {
        this.entity = entity;
    }

    public IntentDto(Id id, String action, String dataType, boolean send, boolean receive) {
        entity = new Intent();

        entity.setId(id);
        entity.setAction(action);
        entity.setDataType(dataType);
        entity.setSend(send);
        entity.setReceive(receive);
    }

    public String getAction() { return entity.getAction(); }
    public String getDataType() { return entity.getDataType(); }
    public boolean getSend() { return entity.getSend(); }
    public boolean getReceive() { return entity.getReceive(); }

    public Intent fromDto() { return entity; }
}
