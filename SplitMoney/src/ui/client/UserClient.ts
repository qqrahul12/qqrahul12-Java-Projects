const BASE_URL = "http://localhost:8080/user";
export async function login(email: string, password: string): Promise<{ success: boolean; message?: string; user?: any }> {
        try {
            const res = await fetch(`${BASE_URL}/login`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                credentials: "include",
                body: JSON.stringify({ email, password }),
            });
            if (res.ok) {
                const data = await res.json();
                return { success: true, user: data.user, message: data.message };
            } else {
                const data = await res.json();
                return { success: false, message: data.message };
            }
        } catch (err) {
            return { success: false, message: "Network error" };
        }
    }
