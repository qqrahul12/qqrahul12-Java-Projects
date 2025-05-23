"use client";
import { useState } from "react";
import { useRouter } from "next/navigation";
import {login} from "../client/UserClient";

export default function LoginForm() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState<string | null>(null);
    const [loading, setLoading] = useState(false);
    const router = useRouter();

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setLoading(true);
        setMessage(null);
        try {
            const res = await login(email, password);
            if (res.success) {
                setMessage("Login successful!");
                router.push("/viewGroups");
            } else {
                setMessage(res.message || "Login failed");
            }
        } catch (err) {
            setMessage("An error occurred. Please try again.");
        } finally {
            setLoading(false);
        }
    };

    return (
        <form className="flex flex-col gap-4 w-80" onSubmit={handleSubmit}>
            <label className="flex flex-col gap-1">
                <span>Email</span>
                <input
                    type="email"
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    className="border rounded px-3 py-2"
                    required
                />
            </label>
            <label className="flex flex-col gap-1">
                <span>Password</span>
                <input
                    type="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    className="border rounded px-3 py-2"
                    required
                />
            </label>
            <button
                type="submit"
                className="bg-blue-600 text-white rounded px-4 py-2 mt-2 hover:bg-blue-700"
                disabled={loading}
            >
                {loading ? "Logging in..." : "Login"}
            </button>
            {message && (
                <div className="mt-2 text-center text-sm text-red-600">{message}</div>
            )}
        </form>
    );
}
