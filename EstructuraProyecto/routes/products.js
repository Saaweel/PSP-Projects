const express = require('express');
const router = express.Router();

router.get('/', (req, res) => {
    res.json("{\"message\": \"Obteniendo información de los productos\"}");
});

router.post('/', (req, res) => {
    res.json("{\"message\": \"Añadiendo un producto\"}");
});

router.get('/category/:category/pdf', (req, res) => {
    const category = req.params.category;
    res.json(`{\"message\": \"Exportando un pdf con los productos de la categoría ${category}\"}`);
});

router.delete('/:id', (req, res) => {
    const productId = req.params.id;
    res.json(`{\"message\": \"Borrando el producto con id ${productId}\"}`);
});

module.exports = router;