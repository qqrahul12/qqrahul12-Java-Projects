'use client'
import React, { useState } from "react";
import { Paper, List, ListItemAvatar, ListItemText, Avatar, Typography, Box, ListItemButton, Button, Dialog, DialogTitle, DialogContent, DialogActions, TextField, CircularProgress, ListItem } from "@mui/material";
import GroupIcon from "@mui/icons-material/Group";
import {GroupChatTabs} from "@app/components/GroupChatTabs";
import { joinGroup } from "@app/client/GroupClient";

export interface Group {
  id: String;
  name: string;
  description?: string;
  groupCode?: string;
  isMember?: boolean;
}

interface AllGroupsListProps {
  groups: Group[];
  onGroupSelect?: (group: Group) => void;
}

const AllGroupsList: React.FC<AllGroupsListProps> = ({ groups }) => {
    const [currentGroup, setCurrentGroup] = React.useState<Group | null>(null);
    const [currentGroupExpenses, setCurrentGroupExpenses] = React.useState<any[]>([]);
    const [joinDialogOpen, setJoinDialogOpen] = useState(false);
    const [selectedGroup, setSelectedGroup] = useState<Group | null>(null);
    const [userName, setUserName] = useState('');
    const [isJoining, setIsJoining] = useState(false);
    const [joinError, setJoinError] = useState('');

    const onGroupClick = async (group: Group) => {
        const res = await fetch(`http://localhost:8080/expense/groups/${group.id}`, { cache: "no-store" });
        const expenseList = await res.json();
        setCurrentGroup(group);
        setCurrentGroupExpenses(expenseList);
    }

    const handleJoinClick = (e: React.MouseEvent, group: Group) => {
        e.stopPropagation(); // Prevent triggering the ListItemButton click
        setSelectedGroup(group);
        setJoinDialogOpen(true);
    };

    const handleJoinGroup = async () => {
        if (!selectedGroup || !userName || !selectedGroup.groupCode) return;
        
        try {
            setIsJoining(true);
            setJoinError('');
            await joinGroup(selectedGroup.groupCode, userName);
            setJoinDialogOpen(false);
            setUserName('');
            // You could add a success notification here
        } catch (error) {
            console.error("Failed to join group:", error);
            setJoinError('Failed to join group. Please try again.');
        } finally {
            setIsJoining(false);
        }
    };

    if (currentGroup) {
        return <GroupChatTabs expenseList={currentGroupExpenses} />
    }

    return (
    <Box display="flex" justifyContent="center" alignItems="center" minHeight="60vh">
      <Paper sx={{width: 480}}>
          <Typography variant="h6" sx={{p: 2}}>
              All Groups
          </Typography>
          <List>
              {groups.map((group) => (
                  <ListItem
                      key={group.groupCode}
                      secondaryAction={
                          group.isMember ? (
                              <Button 
                                  variant="outlined" 
                                  color="primary"
                                  size="small"
                                  onClick={() => onGroupClick(group)}
                              >
                                  View
                              </Button>
                          ) : (
                              <Button 
                                  variant="contained" 
                                  color="primary"
                                  size="small"
                                  onClick={(e) => handleJoinClick(e, group)}
                              >
                                  Join
                              </Button>
                          )
                      }
                  >
                      <ListItemButton onClick={() => onGroupClick(group)}>
                          <ListItemAvatar>
                              <Avatar>
                                  <GroupIcon/>
                              </Avatar>
                          </ListItemAvatar>
                          <ListItemText primary={group.name} secondary={group.description}/>
                      </ListItemButton>
                  </ListItem>
              ))}
          </List>
      </Paper>

      {/* Join Group Dialog */}
      <Dialog open={joinDialogOpen} onClose={() => !isJoining && setJoinDialogOpen(false)}>
          <DialogTitle>Join {selectedGroup?.name}</DialogTitle>
          <DialogContent>
              <Typography variant="body2" color="text.secondary" gutterBottom>
                  Enter your name to join this group.
              </Typography>
              {joinError && (
                  <Typography color="error" variant="body2" sx={{ mb: 2 }}>
                      {joinError}
                  </Typography>
              )}
              <TextField
                  autoFocus
                  margin="dense"
                  label="Your Name"
                  type="text"
                  fullWidth
                  variant="outlined"
                  value={userName}
                  onChange={(e) => setUserName(e.target.value)}
                  disabled={isJoining}
              />
          </DialogContent>
          <DialogActions>
              <Button 
                  onClick={() => setJoinDialogOpen(false)} 
                  disabled={isJoining}
              >
                  Cancel
              </Button>
              <Button 
                  onClick={handleJoinGroup} 
                  variant="contained" 
                  disabled={!userName || isJoining}
                  startIcon={isJoining ? <CircularProgress size={20} color="inherit" /> : null}
              >
                  {isJoining ? 'Joining...' : 'Join'}
              </Button>
          </DialogActions>
      </Dialog>
    </Box>
    );
};

export default AllGroupsList;

