const BASE_URL = 'http://localhost:8080/groups';

export async function createGroup(name: string, description: string) {
    const response = await fetch(BASE_URL, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name,
            description
        }),
    });
    if (!response.ok) {
        const errorText = await response.text();
        console.error('Failed to create group:', response.status, errorText);
        throw new Error('Failed to create group');
    }
}

export async function joinGroup(groupCode: string) {
    const response = await fetch(`${BASE_URL}/joinGroup`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            groupCode
        }),
    });
    
    if (!response.ok) {
        const errorText = await response.text();
        console.error('Failed to join group:', response.status, errorText);
        throw new Error('Failed to join group');
    }
}
