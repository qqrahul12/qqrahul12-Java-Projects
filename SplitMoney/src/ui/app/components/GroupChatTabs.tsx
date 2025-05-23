'use client'
import React, {useState} from "react";
import {Box, Paper, Tab, Tabs, Typography, List, ListItem, ListItemText} from "@mui/material";
import { Expense } from "@app/schema/types";

type GroupChatTabsProps = {
    expenseList: Expense[];
}
export const GroupChatTabs = ({ expenseList } : GroupChatTabsProps) : React.JSX.Element => {
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
                    <List>
                        {expenseList && expenseList.length > 0 ? (
                            expenseList.map((expense, idx) => (
                                <ListItem key={idx}>
                                    <ListItemText
                                        primary={expense.title || `Expense #${idx + 1}`}
                                        secondary={`Amount: ${expense.amount ?? ''} | By: ${expense.paidBy ?? ''}`}
                                    />
                                </ListItem>
                            ))
                        ) : (
                            <Typography>No expenses found.</Typography>
                        )}
                    </List>
                )}
                {tab === 2 && (
                    <Typography>Media files will appear here.</Typography>
                )}
            </Box>
        </Paper>
    );
}

