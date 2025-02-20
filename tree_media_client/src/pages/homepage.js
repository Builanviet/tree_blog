import BlogList from "../components/homepage/blog_list";
import Sidebar from "../components/homepage/sidebar";

export default function Homepage() {
    return <div style={{
        backgroundColor: "#EEEEEE",
        minHeight: "100vh", 
        display: "flex",     
        flexDirection: "row", 
    }}>
        <Sidebar />
        <BlogList />
    </div>
    
}