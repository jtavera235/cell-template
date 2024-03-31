package com.cell.startup;

public class CellApplication {

    public static void main( String[] args ) throws Exception {
        final CellRunner server = new CellRunner();
        server.run(args);
    }
}
