'use client';
import React, { useState } from 'react';
import {
    Container,
    Box,
    Typography,
    TextField,
    Button,
    Paper,
    Avatar
} from '@mui/material';
import GroupIcon from '@mui/icons-material/Group';

const JoinGroup = () => {
    const [groupCode, setGroupCode] = useState('');
    const [userName, setUserName] = useState('');

    const handleJoin = () => {
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
                    <TextField
                        label="Group Code"
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        value={groupCode}
                        onChange={e => setGroupCode(e.target.value)}
                    />
                    <TextField
                        label="Your Name"
                        variant="outlined"
                        fullWidth
                        margin="normal"
                        value={userName}
                        onChange={e => setUserName(e.target.value)}
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        fullWidth
                        sx={{ mt: 2 }}
                        onClick={handleJoin}
                        disabled={!groupCode || !userName}
                    >
                        Join Group
                    </Button>
                </Box>
            </Paper>
        </Container>
    );
};

export default JoinGroup;
