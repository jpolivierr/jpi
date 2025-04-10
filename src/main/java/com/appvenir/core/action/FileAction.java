package com.appvenir.core.action;

import java.io.IOException;

public interface FileAction {
    public void execute() throws IOException;
    public void undo() throws IOException;
}
