'use client'
import React, {useState} from "react";
import {Box, Paper, Tab, Tabs, Typography} from "@mui/material";

export const GroupChatTabs = () : React.JSX.Element => {
    const [tab, setTab] = useState(0);

    return (
        <Paper sx={{ width: 400, minHeight: 320, p: 2 }}>
            <Tabs value={tab} onChange={(_, v) => setTab(v)} centered>
                <Tab label="Messages" />
                <Tab label="Expenses" />
                <Tab label="Media" />
            </Tabs>
            <Box sx={{ mt: 2 }}>
                {tab === 0 && (
                    <Typography>Messages will appear here.</Typography>
                )}
                {tab === 1 && (
                    <Typography>Expenses will appear here.</Typography>
                )}
                {tab === 2 && (
                    <Typography>Media files will appear here.</Typography>
                )}
            </Box>
        </Paper>
    );
}