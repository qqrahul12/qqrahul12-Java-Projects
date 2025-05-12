'use client'
import {Avatar, List, ListItem, ListItemAvatar, ListItemText, Paper, Typography} from "@mui/material";
import GroupIcon from "@mui/icons-material/Group";
import React from "react";

// Dummy data for demonstration
const userGroups = [
    { id: 1, name: "Flatmates", description: "Flatmates group" },
    { id: 2, name: "Office Friends", description: "Work group" },
    { id: 3, name: "Family", description: "Family group" },
];

export const UserGroupsList = (): React.JSX.Element => {
    return (
        <Paper sx={{ width: 320, mb: 4 }}>
            <Typography variant="h6" sx={{ p: 2 }}>
                Your Groups
            </Typography>
            <List>
                {userGroups.map((group) => (
                    <ListItem key={group.id} component={"button"}>
                        <ListItemAvatar>
                            <Avatar>
                                <GroupIcon />
                            </Avatar>
                        </ListItemAvatar>
                        <ListItemText primary={group.name} secondary={group.description} />
                    </ListItem>
                ))}
            </List>
        </Paper>
    );
}