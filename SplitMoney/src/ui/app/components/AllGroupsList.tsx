'use client'
import React from "react";
import { Paper, List, ListItemAvatar, ListItemText, Avatar, Typography, Box, ListItemButton } from "@mui/material";
import GroupIcon from "@mui/icons-material/Group";

export interface Group {
  id: number;
  name: string;
  description?: string;
}

interface AllGroupsListProps {
  groups: Group[];
  onGroupSelect?: (group: Group) => void;
}

const AllGroupsList: React.FC<AllGroupsListProps> = ({ groups, onGroupSelect }) => {
    return (
    <Box display="flex" justifyContent="center" alignItems="center" minHeight="60vh">
      <Paper sx={{width: 320}}>
          <Typography variant="h6" sx={{p: 2}}>
              All Groups
          </Typography>
          <List>
              {groups.map((group) => (
                  <ListItemButton
                      key={group.id}
                      onClick={() => onGroupSelect && onGroupSelect(group)}
                  >
                      <ListItemAvatar>
                          <Avatar>
                              <GroupIcon/>
                          </Avatar>
                      </ListItemAvatar>
                      <ListItemText primary={group.name} secondary={group.description}/>
                  </ListItemButton>
              ))}
          </List>
      </Paper>
    </Box>
    );
};

export default AllGroupsList;
