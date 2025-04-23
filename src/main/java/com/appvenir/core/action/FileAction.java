package com.appvenir.core.action;

import java.io.IOException;

public interface FileAction extends Action{
    public void undo() throws IOException;
}
