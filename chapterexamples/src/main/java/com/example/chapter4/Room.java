package com.example.chapter4;

import java.util.List;

public record Room(String name, String description, List<String> exits) {

    public String getExits() {
        StringBuilder exitDesc = new StringBuilder();

        if (exits.isEmpty()) {
            exitDesc.append("There are no obvios exits.");
        } else if (exits.size() == 1) {
            exitDesc.append("There is an exit to the ");
            exitDesc.append(exits.get(0));
        } else if (exits.size() == 2) {
            exitDesc.append(exits.get(0));
            exitDesc.append(" and ");
            exitDesc.append(exits.get(1));
        } else {
            exitDesc.append("There are exits to the ");

            boolean first = true;

            for (String exit : exits) {
                if (!first) {
                    exitDesc.append(", ");
                } else {
                    first = false;
                }
                exitDesc.append(exit);
            }
        }
        exitDesc.append(".");

        return exitDesc.toString();
    }
    
}
