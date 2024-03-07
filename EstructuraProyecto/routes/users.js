const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {
    res.json("{\"message\": \"Obteniendo información de los usuarios\"}");
});

router.post('/login', (req, res) => {
    res.json("{\"message\": \"Logueando un usuario\"}");
});

module.exports = router;