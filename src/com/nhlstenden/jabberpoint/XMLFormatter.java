package com.nhlstenden.jabberpoint;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class XMLFormatter
{
    private File fileToFormat;

    public XMLFormatter(File fileToFormat)
    {
        this.fileToFormat = fileToFormat;
    }

    public File getFileToFormat()
    {
        return this.fileToFormat;
    }

    public void setFileToFormat(File fileToFormat)
    {
        this.fileToFormat = fileToFormat;
    }

    public void formatTheXml()
    {
        try {
            String [] headers = {"</presentation>", "<presentation>", "<slide>", "/<slide>"};
            List<String> linesFromFileToFormat = Files.readAllLines(fileToFormat.toPath());
            PrintWriter out = new PrintWriter(new FileWriter(fileToFormat));


            for(String line : linesFromFileToFormat)
            {
                boolean isHeaderFound = false;

                for(String header : headers)
                {
                    if(line.trim().equalsIgnoreCase(header)) {
                        isHeaderFound = true;
                        break;
                    }
                }

                if(isHeaderFound) {
                    out.println(line);
                } else {
                    out.print(line);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
