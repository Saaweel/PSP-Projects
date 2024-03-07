const express = require("express");
const router = express.Router();

let data = require("../data/compras");

router.get("/:id/productos", (req, res) => {
    const compra = data.find(compra => compra.id === req.params.id);
    
    if (compra) {
        res.json(compra.productos);
    } else {
        res.status(404).send("Compra no encontrada");
    }
});

router.get("/:id/numProductos", (req, res) => {
    const compra = data.find(compra => compra.id === req.params.id);
    
    if (compra) {
        res.json(compra.productos.length);
    } else {
        res.status(404).send("Compra no encontrada");
    }
});

module.exports = router;