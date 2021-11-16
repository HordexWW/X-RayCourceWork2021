package com.sshmygin.xray.handler;

import com.sshmygin.xray.model.TableData;

public interface DataHandler {
    TableData getHandledData(TableData beforeData);
}
