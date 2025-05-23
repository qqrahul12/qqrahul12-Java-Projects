const BASE_URL = 'http://localhost:8080/groups';

export async function createGroup(name: string, description: string) {
    // Possible reasons for fetch failing:
    // 1. CORS is not enabled on your backend (Spring Boot). Make sure you allow requests from http://localhost:3000.
    // 2. The endpoint URL or HTTP method is incorrect. Double-check your backend controller mapping.
    // 3. The backend server is not running or not accessible at http://localhost:8080.
    // 4. The backend expects different request body fields or structure.
    // 5. There may be a trailing slash issue; try removing the trailing slash from the URL if your backend does not expect it.// Ensure this is correct

    const response = await fetch(BASE_URL, { // removed trailing slash
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            name,
            description,
            creatorId: "d17c295a-ac56-4ad1-8a98-f75251e883b3"
        }),
    });
    if (!response.ok) {
        // To debug, you can log the response status and body:
        const errorText = await response.text();
        console.error('Failed to create group:', response.status, errorText);
        throw new Error('Failed to create group');
    }
}

export async function joinGroup(groupCode: string, userName: string) {
    const response = await fetch(`${BASE_URL}/joinGroup`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            groupCode,
            userId: userName // Using userName as userId for now
        }),
    });
    
    if (!response.ok) {
        const errorText = await response.text();
        console.error('Failed to join group:', response.status, errorText);
        throw new Error('Failed to join group');
    }
}
