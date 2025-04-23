package com.appvenir.core;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.jline.utils.AttributedStringBuilder;
import org.jline.utils.AttributedStyle;
import java.io.IOException;
import java.util.List;

public class CliPrompter {

    private final Terminal terminal;

    public CliPrompter() throws IOException {
        terminal = TerminalBuilder.builder()
                .system(true)
                .build();
    }

    public String promptForOption(String title, List<String> options) throws IOException {
        int selected = 0;
    
        terminal.writer().println();
        terminal.writer().println(title);
        terminal.flush();
    
        boolean selecting = true;
        while (selecting) {
            // Clear line and reprint options
            terminal.puts(org.jline.utils.InfoCmp.Capability.carriage_return);
            terminal.puts(org.jline.utils.InfoCmp.Capability.clr_eol);
    
            AttributedStringBuilder sb = new AttributedStringBuilder();
            for (int i = 0; i < options.size(); i++) {
                if (i == selected) {
                    sb.style(AttributedStyle.DEFAULT.background(AttributedStyle.BLUE).foreground(AttributedStyle.WHITE));
                    sb.append(" " + options.get(i) + " ");
                    sb.style(AttributedStyle.DEFAULT);
                    sb.append("  ");
                } else {
                    sb.append(" " + options.get(i) + "   ");
                }
            }
    
            terminal.writer().print(sb.toAnsi());
            terminal.flush();
    
            int ch = terminal.reader().read();
    
            if (ch == 10) { // ENTER key
                selecting = false;
            } else if (ch == 27) { // ESC
                // Arrow keys start with ESC
                int next1 = terminal.reader().read();
                int next2 = terminal.reader().read();
                if (next1 == 91) {
                    if (next2 == 67) { // Right arrow
                        selected = (selected + 1) % options.size();
                    } else if (next2 == 68) { // Left arrow
                        selected = (selected - 1 + options.size()) % options.size();
                    }
                }
            }
        }
    
        terminal.writer().println(); // move to next line after selection
        terminal.flush();
        return options.get(selected);
    }
    
}

