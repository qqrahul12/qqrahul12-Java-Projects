'use client';
import {Avatar, Box} from "@mui/material";
import PersonIcon from "@mui/icons-material/Person";
import React from "react";

export const  ProfileIcon = (): React.JSX.Element => {
    return (
        <Box sx={{ position: "absolute", top: 24, right: 24 }}>
            <Avatar>
                <PersonIcon />
            </Avatar>
        </Box>
    );
}