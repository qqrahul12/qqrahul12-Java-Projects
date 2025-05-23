'use client';
import { useState } from "react";
import {createUser} from "@app/client/UserClient";
import {useRouter} from "next/navigation";

export default function SignupForm() {
    const [form, setForm] = useState({
        firstname: "",
        lastname: "",
        email: "",
        phoneNumber: "",
        password: ""
    });

    const [isLoading, setIsLoading] = useState(false);
    const router = useRouter();

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setIsLoading(true);
        try {
            await createUser(form);
            router.push("/");
            setForm({
                firstname: "",
                lastname: "",
                email: "",
                phoneNumber: "",
                password: ""
            });
        } catch (err: any) {
            alert(err?.message || "Failed to create user.");
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <form className="flex flex-col gap-4 w-full sm:w-80" onSubmit={handleSubmit}>
            <div className="flex gap-[10px]">
                <label className="flex flex-col gap-1 flex-1">
                    <span className="font-medium">First Name</span>
                    <input
                        type="text"
                        name="firstname"
                        value={form.firstname}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                        required
                    />
                </label>
                <label className="flex flex-col gap-1 flex-1">
                    <span className="font-medium">Last Name</span>
                    <input
                        type="text"
                        name="lastname"
                        value={form.lastname}
                        onChange={handleChange}
                        className="border rounded px-3 py-2 w-full"
                        required
                    />
                </label>
            </div>
            <label className="flex flex-col gap-1 w-full">
                <span className="font-medium">Email</span>
                <input
                    type="email"
                    name="email"
                    value={form.email}
                    onChange={handleChange}
                    className="border rounded px-3 py-2 w-full"
                    required
                />
            </label>
            <label className="flex flex-col gap-1 w-full">
                <span className="font-medium">Phone</span>
                <input
                    type="tel"
                    name="phoneNumber"
                    value={form.phoneNumber}
                    onChange={handleChange}
                    className="border rounded px-3 py-2 w-full"
                    required
                />
            </label>
            <label className="flex flex-col gap-1 w-full">
                <span className="font-medium">Password</span>
                <input
                    type="password"
                    name="password"
                    value={form.password}
                    onChange={handleChange}
                    className="border rounded px-3 py-2 w-full"
                    required
                />
            </label>
            <button
                type="submit"
                className={`mt-4 py-2 rounded font-semibold w-full ${
                    isLoading ? "bg-gray-400 cursor-not-allowed" : "bg-blue-600 hover:bg-blue-700 text-white"
                }`}
                disabled={isLoading}
            >
                {isLoading ? "Creating..." : "Create Account"}
            </button>
        </form>
    );
}

