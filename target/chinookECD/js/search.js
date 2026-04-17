/**
 * JAVASCRIPT - search.js
 * Gestiona búsqueda en tiempo real y ordenamiento de tablas
 * Clase SearchManager encapsula toda la lógica de cliente
 */

// Sistema de búsqueda en tiempo real
class SearchManager {
    // Constructor: inicializa variables
    constructor() {
        this.searchInput = null;      // Campo de entrada de búsqueda
        this.tableBody = null;        // Cuerpo de la tabla HTML
        this.originalRows = [];       // Guardar filas originales
        this.sortOrder = {};          // Orden de clasificación
        this.sortSelect = null;       // Selector de ordenamiento
    }

    /**
     * Inicializa el gestor de búsqueda
     * @param searchInputId ID del input de búsqueda
     * @param tableBodyId ID del tbody de la tabla
     * @param sortSelectId ID del select de ordenamiento (opcional)
     */
    init(searchInputId, tableBodyId, sortSelectId = null) {
        // Obtener elementos del DOM
        this.searchInput = document.getElementById(searchInputId);
        this.tableBody = document.getElementById(tableBodyId);
        this.sortSelect = sortSelectId ? document.getElementById(sortSelectId) : null;

        // Validar que existan los elementos
        if (!this.searchInput || !this.tableBody) {
            return false;
        }

        // Guardar copias de las filas originales
        this.originalRows = Array.from(this.tableBody.querySelectorAll('tr'));

        // Listeners para búsqueda en tiempo real
        this.searchInput.addEventListener('input', (e) => this.filterAndSort(e.target.value));
        this.searchInput.addEventListener('keyup', (e) => this.filterAndSort(e.target.value));

        // Listeners para ordenamiento
        this.addSortListeners();

        // Listener para dropdown de ordenamiento
        if (this.sortSelect) {
            this.sortSelect.addEventListener('change', (e) => this.handleSortSelectChange(e.target.value));
        }

        return true;
    }

    /**
     * Maneja cambios en el dropdown de ordenamiento
     * Extrae columna y dirección, luego ordena
     */
    handleSortSelectChange(sortValue) {
        if (!sortValue) return;

        // Dividir valor en columna y dirección (ej: "nombre-asc")
        const [column, direction] = sortValue.split('-');
        this.sortByColumn(column, direction);
    }

    /**
     * Ordena la tabla por una columna específica
     * @param column Nombre de la columna
     * @param direction 'asc' o 'desc'
     */
    sortByColumn(column, direction) {
        // Obtener índice de la columna
        const columnIndex = this.getColumnIndex(column);
        if (columnIndex === -1) return;

        // Obtener solo filas visibles
        const visibleRows = this.originalRows.filter(row => row.style.display !== 'none');
        
        // Ordenar las filas
        visibleRows.sort((a, b) => {
            // Obtener valores de celdas
            const aValue = a.children[columnIndex].textContent.trim();
            const bValue = b.children[columnIndex].textContent.trim();

            // Intentar convertir a números
            const aNum = parseFloat(aValue);
            const bNum = parseFloat(bValue);

            let comparison = 0;
            // Si son números, comparar numéricamente
            if (!isNaN(aNum) && !isNaN(bNum)) {
                comparison = aNum - bNum;
            } else {
                // Si no, comparar alfabéticamente
                comparison = aValue.localeCompare(bValue, 'es');
            }

            // Invertir si es descendente
            return direction === 'asc' ? comparison : -comparison;
        });

        // Re-insertar filas en el orden correcto
        visibleRows.forEach(row => {
            this.tableBody.appendChild(row);
        });
    }

    /**
     * Obtiene el índice de una columna por su nombre
     * Maneja variaciones de nombres (singular, plural, idiomas)
     */
    getColumnIndex(columnName) {
        // Mapeo de equivalencias de columnas
        const headers = document.querySelectorAll('th[data-sortable]');
        const mappings = {
            'id': ['id'],
            'nombre': ['nombre', 'name'],
            'name': ['nombre', 'name'],
            'título': ['título', 'title'],
            'title': ['título', 'title'],
            'artista': ['artista', 'artist'],
            'artist': ['artista', 'artist']
        };
        
        const normalizedColumn = columnName.toLowerCase();
        const equivalents = mappings[normalizedColumn] || [normalizedColumn];
        
        // Buscar coincidencia en headers
        for (let i = 0; i < headers.length; i++) {
            const headerText = headers[i].textContent.trim().replace(/ ↑| ↓/g, '').toLowerCase();
            for (let equiv of equivalents) {
                if (headerText.includes(equiv)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Agrega listeners de clic para ordenamiento en headers
     */
    /**
     * Agrega listeners de clic para ordenamiento en headers
     */
    addSortListeners() {
        // Obtener headers con atributo sortable
        const headers = document.querySelectorAll('th[data-sortable]');
        headers.forEach(header => {
            // Cambiar cursor a pointer para indicar que es clickeable
            header.style.cursor = 'pointer';
            // Agregar evento de clic
            header.addEventListener('click', (e) => this.sortTable(e.target));
        });
    }

    /**
     * Alterna el orden de clasificación (asc/desc) de una columna
     * @param header Elemento header clickeado
     */
    sortTable(header) {
        // Obtener índice de la columna
        const columnIndex = Array.from(header.parentNode.children).indexOf(header);
        const columnName = header.textContent.trim().replace(/ ↑| ↓/g, '');
        
        // Determinar nueva dirección de ordenamiento
        if (!this.sortOrder[columnName]) {
            this.sortOrder[columnName] = 'asc';
        } else if (this.sortOrder[columnName] === 'asc') {
            this.sortOrder[columnName] = 'desc';
        } else {
            this.sortOrder[columnName] = 'asc';
        }

        // Limpiar todos los indicadores de orden previos
        document.querySelectorAll('th[data-sortable]').forEach(th => {
            th.innerHTML = th.innerHTML.replace(/ ↑| ↓/g, '');
        });

        // Agregar indicador visual del nuevo orden
        const arrow = this.sortOrder[columnName] === 'asc' ? ' ↑' : ' ↓';
        header.innerHTML += arrow;

        // Obtener solo las filas visibles
        const visibleRows = this.originalRows.filter(row => row.style.display !== 'none');
        
        // Ordenar las filas visibles
        visibleRows.sort((a, b) => {
            const aValue = a.children[columnIndex].textContent.trim();
            const bValue = b.children[columnIndex].textContent.trim();

            // Intentar convertir a números
            const aNum = parseFloat(aValue);
            const bNum = parseFloat(bValue);

            let comparison = 0;
            if (!isNaN(aNum) && !isNaN(bNum)) {
                // Comparación numérica
                comparison = aNum - bNum;
            } else {
                // Comparación alfabética en español
                comparison = aValue.localeCompare(bValue, 'es');
            }

            // Aplicar dirección del orden
            return this.sortOrder[columnName] === 'asc' ? comparison : -comparison;
        });

        // Re-insertar filas en nuevo orden
        visibleRows.forEach(row => {
            this.tableBody.appendChild(row);
        });
    }

    /**
     * Filtra filas según el término de búsqueda
     * Implementa búsqueda en tiempo real sin recargar página
     * @param searchTerm Texto a buscar
     */
    filterAndSort(searchTerm) {
        // Normalizar búsqueda
        const term = searchTerm.toLowerCase().trim();

        // Si está vacío, mostrar todas las filas
        if (term === '') {
            this.originalRows.forEach(row => {
                row.style.display = '';
            });
            this.removeNoResults();
            return;
        }

        let visibleCount = 0;

        // Iterar cada fila y mostrar/ocultar según coincidencia
        this.originalRows.forEach(row => {
            const text = row.textContent.toLowerCase();
            if (text.includes(term)) {
                row.style.display = '';
                visibleCount++;
            } else {
                row.style.display = 'none';
            }
        });

        // Mostrar mensaje si no hay resultados
        if (visibleCount === 0) {
            this.showNoResults();
        } else {
            this.removeNoResults();
        }
    }

    /**
     * Muestra mensaje cuando no hay resultados
     */
    showNoResults() {
        // Verificar si el mensaje ya existe
        if (document.getElementById('no-results-message')) {
            return;
        }

        // Crear fila con mensaje
        const noResultsRow = document.createElement('tr');
        noResultsRow.id = 'no-results-message';
        noResultsRow.innerHTML = `
            <td colspan="100%" style="text-align: center; padding: 20px; color: #999; font-style: italic;">
                No se encontraron resultados
            </td>
        `;
        this.tableBody.appendChild(noResultsRow);
    }

    /**
     * Elimina mensaje de "sin resultados"
     */
    removeNoResults() {
        const noResultsRow = document.getElementById('no-results-message');
        if (noResultsRow) {
            noResultsRow.remove();
        }
    }

    /**
     * Limpia el campo de búsqueda
     */
    clear() {
        if (this.searchInput) {
            this.searchInput.value = '';
            this.filterAndSort('');
        }
    }
}

// Instancia global del gestor de búsqueda
const searchManager = new SearchManager();

// Ejecutar cuando la página cargue completamente
document.addEventListener('DOMContentLoaded', function() {
    // Intentar inicializar para diferentes tablas
    const searchConfigs = [
        { inputId: 'search-input', bodyId: 'table-body', sortId: 'sort-select' },
        { inputId: 'artist-search', bodyId: 'artist-table-body', sortId: 'sort-select' },
        { inputId: 'genre-search', bodyId: 'genre-table-body', sortId: 'sort-select' },
        { inputId: 'album-search', bodyId: 'album-table-body', sortId: 'sort-select' },
        { inputId: 'playlist-search', bodyId: 'playlist-table-body', sortId: 'sort-select' }
    ];

    for (let config of searchConfigs) {
        if (searchManager.init(config.inputId, config.bodyId, config.sortId)) {
            console.log(`Search initialized for ${config.inputId}`);
            break;
        }
    }
});
