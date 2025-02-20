import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoginForm from '../pages/login.js';
import Homepage from '../pages/homepage.js';
import Header from '../components/shared/header.js';

const AppRoutes = () => (
    <Router>
        <Header/>
        <Routes>
            <Route path="/login" element={<LoginForm />} />
            <Route path="/homepage" element={<Homepage />} />
        </Routes>
    </Router>
);

export default AppRoutes;
