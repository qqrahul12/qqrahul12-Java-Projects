'use client';
import React, { useState } from 'react';
import {
    Container,
    Box,
    Typography,
    TextField,
    Button,
    Paper,
    Avatar,
    Link,
    Dialog,
    DialogTitle,
    DialogContent,
    DialogActions,
    CircularProgress,
    Alert
} from '@mui/material';
import GroupIcon from '@mui/icons-material/Group';
import {createGroup, joinGroup} from "@app/client/GroupClient";
import NextLink from 'next/link'; // Import Next.js Link for client-side navigation

const JoinGroup = () => {
    const [groupCode, setGroupCode] = useState('');
    const [userName, setUserName] = useState('');
    const [showCreateGroup, setShowCreateGroup] = useState(false);
    const [newGroupName, setNewGroupName] = useState('');
    const [newGroupDesc, setNewGroupDesc] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const [isJoiningLoading, setIsJoiningLoading] = useState(false); 
    const [joinError, setJoinError] = useState('');

    const handleJoinGroup = async () => {
        try {
            console.log('Joining group with code:', groupCode, 'and name:', userName);
            setIsJoiningLoading(true);
            setJoinError('');
            await joinGroup(groupCode, userName);
            
            // Reset fields on success
            setGroupCode('');
            setUserName('');
            
            // You could also redirect to the group page here
            // For example: router.push(`/groups/${response.groupId}`);
            
        } catch (error) {
            console.error("Failed to join group:", error);
            setJoinError('Failed to join group. Please check the group code and try again.');
        } finally {
            setIsJoiningLoading(false);
        }
    };

    const handleCreateGroup = async () => {
        try {
            setIsLoading(true);
            await createGroup(newGroupName, newGroupDesc);
            setShowCreateGroup(false);
            setNewGroupName('');
            setNewGroupDesc('');
        } catch (error) {
            console.error("Failed to create group:", error);
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <Container maxWidth="sm">
            <Paper elevation={3} sx={{ p: 4, mt: 8 }}>
                <Box display="flex" flexDirection="column" alignItems="center">
                    <Avatar sx={{ bgcolor: 'primary.main', mb: 2 }}>
                        <GroupIcon />
                    </Avatar>
                    <Typography variant="h5" gutterBottom>
                        Join a SuperChat Group
                    </Typography>
                    
                    {joinError && (
                        <Alert severity="error" sx={{ width: '100%', mb: 2 }}>
                            {joinError}
                        </Alert>
                    )}
                    
                    <TextField
                        label="Group Code"
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        value={groupCode}
                        onChange={e => setGroupCode(e.target.value)}
                        disabled={isJoiningLoading}
                    />
                    <TextField
                        label="Your Name"
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        value={userName}
                        onChange={e => setUserName(e.target.value)}
                        disabled={isJoiningLoading}
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        fullWidth
                        sx={{ mt: 2 }}
                        onClick={handleJoinGroup}
                        disabled={!groupCode || !userName || isJoiningLoading}
                        startIcon={isJoiningLoading ? <CircularProgress size={20} color="inherit" /> : null}
                    >
                        {isJoiningLoading ? 'Joining...' : 'Join Group'}
                    </Button>
                </Box>
            </Paper>
            <Box mt={2} display="flex" justifyContent="center" flexDirection="column" alignItems="center">
                <Typography variant="body2">
                    or{' '}
                    <Link
                        component="button"
                        variant="body2"
                        onClick={() => setShowCreateGroup(true)}
                        underline="hover"
                    >
                        Create a new group
                    </Link>
                </Typography>
                <Typography variant="body2" mt={1}>
                    <NextLink href="/viewGroups" passHref>
                        <Link component="a" variant="body2" underline="hover">
                            View all groups
                        </Link>
                    </NextLink>
                </Typography>
            </Box>
            <Dialog open={showCreateGroup} onClose={() => !isLoading && setShowCreateGroup(false)}>
                <DialogTitle>Create New Group</DialogTitle>
                <DialogContent>
                    <TextField
                        label="Group Name"
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        value={newGroupName}
                        onChange={e => setNewGroupName(e.target.value)}
                        disabled={isLoading}
                    />
                    <TextField
                        label="Description"
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        value={newGroupDesc}
                        onChange={e => setNewGroupDesc(e.target.value)}
                        disabled={isLoading}
                    />
                </DialogContent>
                <DialogActions>
                    <Button 
                        onClick={() => setShowCreateGroup(false)} 
                        disabled={isLoading}
                    >
                        Cancel
                    </Button>
                    <Button
                        variant="contained"
                        onClick={handleCreateGroup}
                        disabled={!newGroupName || isLoading}
                        startIcon={isLoading ? <CircularProgress size={20} color="inherit" /> : null}
                    >
                        {isLoading ? 'Creating...' : 'Create'}
                    </Button>
                </DialogActions>
            </Dialog>
        </Container>
    );
};

export default JoinGroup;

