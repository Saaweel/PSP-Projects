const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {
    res.json("{\"message\": \"Obteniendo información de los clientes\"}");
});

router.delete('/:id', (req, res) => {
    const clientId = req.params.id;
    res.json(`{\"message\": \"Borrando el cliente con id ${clientId}\"}`);
});

router.get('/frequent', (req, res) => {
    res.json("{\"message\": \"Obteniendo el cliente más habitual\"}");
});

module.exports = router;