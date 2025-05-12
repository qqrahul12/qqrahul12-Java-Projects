import AllGroupsList from "@app/components/AllGroupsList";

export default async function Groups() {
    const res = await fetch("http://localhost:8080/groups", { cache: "no-store" });
    const groups = await res.json();

    // Handler for group selection
    const handleGroupSelect = (group: any) => {
        console.log("Selected group:", group);
    };

    return (
        <AllGroupsList groups={groups} onGroupSelect={handleGroupSelect} />
    );
}
